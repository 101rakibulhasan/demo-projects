import 'package:http/http.dart' as http;
import 'dart:convert';

String apiURL = "https://jsonplaceholder.typicode.com/";

// this function requests api to get all posts
Future<List<dynamic>> getPosts() async {
  try {
    final response = await http.get(Uri.parse('${apiURL}posts'));

    if (response.statusCode == 200) {
      return json.decode(response.body);
    } else {
      throw Exception('Failed to load posts');
    }
  } catch (e) {
    print('Error fetching posts: $e');
    return [];
  }
}

// this function requests api to delete post
Future<void> deletePost(int id) async {
  try {
    final response = await http.delete(Uri.parse('${apiURL}posts/$id'));

    if (response.statusCode == 200) {
      return json.decode(response.body);
    } else {
      throw Exception('Failed to delete post');
    }
  } catch (e) {
    print('Error deleting post: $e');
    return;
  }
}

// this function requests api to update post
Future<void> updatePost(int userID, int id, String title, String body) async {
  try {
    final response = await http.put(
      Uri.parse('${apiURL}posts/$id'),
      headers: {'Content-Type': 'application/json; charset=UTF-8'},
      body: jsonEncode({
        'userId': userID,
        'id': id,
        'title': title,
        'body': body,
      }),
    );
    if (response.statusCode == 200) {
      return json.decode(response.body);
    } else {
      throw Exception('Failed to update post');
    }
  } catch (e) {
    print('Error updating post: $e');
    return;
  }
}

// this function requests api to create post
Future<void> createPost(int userID, int id, String title, String body) async {
  try {
    final response = await http.post(
      Uri.parse('${apiURL}posts'),
      headers: {'Content-Type': 'application/json; charset=UTF-8'},
      body: jsonEncode({
        'userId': userID,
        'id': id,
        'title': title,
        'body': body,
      }),
    );
    if (response.statusCode == 200) {
      return json.decode(response.body);
    } else {
      throw Exception('Failed to load posts');
    }
  } catch (e) {
    print('Error fetching posts: $e');
    return;
  }
}
