import "package:flutter_bloc/flutter_bloc.dart";
import "package:flutter_crud_bloc_app/crud.dart";
import "post_event.dart";
import "post_state.dart";

class PostBloc extends Bloc<PostEvent, PostState> {
  PostBloc() : super(PostState([])) {
    //  calls GetPosts whic sends request and gets all the post in the API and stores it in PostState post list
    on<GetPostsCalled>((event, emit) async {
      final posts = await getPosts();
      emit(PostState(posts));
    });

    //  calls DeletePost whic sends request and delets post with the ppost ID mentioned in  posts list variable
    on<DeletePostCalled>((event, emit) async {
      await deletePost(event.postID);
      final posts = state.posts;
      posts.removeWhere((post) => post['id'] == event.postID);
      emit(PostState(posts));
    });

    //  calls UpdatePost whic sends request and updates post with the ppost ID mentioned in  posts list variable
    on<UpdatePostCalled>((event, emit) async {
      await updatePost(event.userID, event.postID, event.title, event.body);
      final posts = state.posts;
      final postItem = {
        'userId': event.userID,
        'id': event.postID,
        'title': event.title,
        'body': event.body,
      };
      final index = posts.indexWhere((post) => post['id'] == event.postID);
      if (index != -1) {
        posts[index] = postItem;
      }
      emit(PostState(posts));
    });

    //  calls CreatePost whic sends request and adds new post in  posts list variable
    on<CreatePostCalled>((event, emit) async {
      await createPost(event.userID, event.postID, event.title, event.body);
      final posts = state.posts;
      final postItem = {
        'userId': event.userID,
        'id': event.postID,
        'title': event.title,
        'body': event.body,
      };
      posts.add(postItem);
      emit(PostState(posts));
    });
  }
}
