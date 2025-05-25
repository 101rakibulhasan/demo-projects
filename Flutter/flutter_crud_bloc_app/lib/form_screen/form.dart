import 'package:flutter/material.dart';
import 'package:flutter_crud_bloc_app/posts/post_bloc.dart';
import 'package:flutter_crud_bloc_app/posts/post_event.dart';
import 'package:flutter_crud_bloc_app/posts/post_state.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class FormView extends StatefulWidget {
  final int createorupdate;
  final int userID;
  final int id;
  final String title;
  final String body;
  const FormView({
    super.key,
    required this.createorupdate,
    required this.userID,
    required this.id,
    required this.title,
    required this.body,
  });

  @override
  State<FormView> createState() => _FormViewState();
}

class _FormViewState extends State<FormView> {
  @override
  Widget build(BuildContext context) {
    final TextEditingController titleC = TextEditingController(
      text: widget.title,
    );
    final TextEditingController bodyC = TextEditingController(
      text: widget.body,
    );

    final postBloc = context.read<PostBloc>();

    return BlocBuilder<PostBloc, PostState>(
      builder: (context, state) {
        return Scaffold(
          body: Column(
            children: [
              Row(
                children: [
                  IconButton(
                    onPressed: () {
                      Navigator.pop(context);
                    },
                    icon: Icon(Icons.arrow_back),
                  ),
                  Text(
                    (widget.createorupdate == 0)
                        ? "Create Post"
                        : "Update Post",
                  ),
                ],
              ),

              Column(
                children: [
                  TextField(
                    controller: titleC,
                    decoration: InputDecoration(
                      hintText: "Enter title",
                      labelText: "Title",
                    ),
                  ),
                  TextField(
                    controller: bodyC,
                    decoration: InputDecoration(
                      hintText: "Enter body",
                      labelText: "Body",
                    ),
                  ),
                  ElevatedButton(
                    onPressed: () {
                      String titleText = titleC.text;
                      String bodyText = bodyC.text;
                      if (titleText.isEmpty || bodyText.isEmpty) {
                        ScaffoldMessenger.of(context).showSnackBar(
                          SnackBar(
                            content: Text("Title and Body cannot be empty"),
                            duration: Duration(seconds: 2),
                          ),
                        );
                        return;
                      }
                      if (widget.createorupdate == 0) {
                        postBloc.add(
                          CreatePostCalled(1, 100, titleText, bodyText),
                        );
                      } else {
                        postBloc.add(
                          UpdatePostCalled(
                            widget.userID,
                            widget.id,
                            titleText,
                            bodyText,
                          ),
                        );
                      }
                    },
                    child: Text(
                      (widget.createorupdate == 0) ? "Post" : "Update",
                    ),
                  ),
                ],
              ),
            ],
          ),
        );
      },
    );
  }
}
