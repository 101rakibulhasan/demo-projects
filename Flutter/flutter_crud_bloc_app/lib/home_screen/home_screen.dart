import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_crud_bloc_app/posts/post_bloc.dart';
import 'package:flutter_crud_bloc_app/posts/post_event.dart';
import 'package:flutter_crud_bloc_app/posts/post_state.dart';

import 'package:flutter_crud_bloc_app/form_screen/form.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  @override
  void initState() {
    super.initState();
    context.read<PostBloc>().add(GetPostsCalled());
  }

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<PostBloc, PostState>(
      builder: (context, state) {
        return Column(
          children: [
            Expanded(
              child: ListView.builder(
                itemCount: state.posts.length,
                itemBuilder: (context, index) {
                  return PostsItem(
                    userID: state.posts[index]['userId'],
                    id: state.posts[index]['id'],
                    title: state.posts[index]['title'],
                    body: state.posts[index]['body'],
                    onDelete: () {
                      state.posts.removeAt(index);
                      setState(() {});
                    },
                  );
                },
              ),
            ),
            Container(
              margin: EdgeInsets.all(10),
              child: ElevatedButton(
                onPressed: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) {
                        return BlocProvider.value(
                          value: context.read<PostBloc>(),
                          child: FormView(
                            createorupdate: 0,
                            userID: 0,
                            id: 0,
                            title: "",
                            body: "",
                          ),
                        );
                      },
                    ),
                  );
                },
                child: Text("Post"),
              ),
            ),
          ],
        );
      },
    );
  }
}

class PostsItem extends StatefulWidget {
  final int userID;
  final int id;
  final String title;
  final String body;
  final VoidCallback onDelete;
  const PostsItem({
    super.key,
    required this.userID,
    required this.id,
    required this.title,
    required this.body,
    required this.onDelete,
  });

  @override
  State<PostsItem> createState() => _PostsItemState();
}

class _PostsItemState extends State<PostsItem> {
  @override
  Widget build(BuildContext context) {
    final postBloc = context.read<PostBloc>();

    return Container(
      padding: EdgeInsets.all(10),
      margin: EdgeInsets.all(10),
      decoration: BoxDecoration(
        color: Colors.white,
        boxShadow: [BoxShadow(color: Colors.black, blurRadius: 10.0)],
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            children: [
              Text("USER ID: "),
              Expanded(child: Text(widget.userID.toString())),
            ],
          ),
          Row(
            children: [
              Text("ID: "),
              Expanded(child: Text(widget.id.toString())),
            ],
          ),
          Row(children: [Text("TITLE: "), Expanded(child: Text(widget.title))]),
          Row(children: [Text("BODY: "), Expanded(child: Text(widget.body))]),
          Row(
            mainAxisAlignment: MainAxisAlignment.end,
            children: [
              IconButton(
                icon: Icon(Icons.edit),
                onPressed: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) {
                        return BlocProvider.value(
                          value: context.read<PostBloc>(),
                          child: FormView(
                            createorupdate: 1,
                            userID: widget.userID,
                            id: widget.id,
                            title: widget.title,
                            body: widget.body,
                          ),
                        );
                      },
                    ),
                  );
                },
              ),
              IconButton(
                icon: Icon(Icons.delete),
                onPressed: () async {
                  try {
                    postBloc.add(DeletePostCalled(widget.id));
                    widget.onDelete();
                  } catch (e) {
                    if (mounted) {
                      ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(content: Text('Error: ${e.toString()}')),
                      );
                    }
                  }
                },
              ),
            ],
          ),
        ],
      ),
    );
  }
}
