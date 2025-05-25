abstract class PostEvent {}

class GetPostsCalled extends PostEvent {}

class DeletePostCalled extends PostEvent {
  final int postID;

  DeletePostCalled(this.postID);
}

class UpdatePostCalled extends PostEvent {
  final int userID;
  final int postID;
  final String title;
  final String body;

  UpdatePostCalled(this.userID, this.postID, this.title, this.body);
}

class CreatePostCalled extends PostEvent {
  final int userID;
  final int postID;
  final String title;
  final String body;

  CreatePostCalled(this.userID, this.postID, this.title, this.body);
}
