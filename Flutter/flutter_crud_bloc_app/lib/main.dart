import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_crud_bloc_app/home_screen/home_screen.dart';
import 'posts/post_bloc.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

// The MyApp class together with MultiBlocProvider
class _MyAppState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {
    return MultiBlocProvider(
      providers: [BlocProvider(create: (context) => PostBloc())],
      child: MaterialApp(
        debugShowCheckedModeBanner: false,
        title: 'Flutter CRUD + Bloc',
        home: Scaffold(body: HomeScreen()),
      ),
    );
  }
}
