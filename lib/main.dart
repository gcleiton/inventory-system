import 'package:flutter/material.dart';

void main() => runApp(InventorySystem());

class InventorySystem extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData.light().copyWith(primaryColor: Colors.red),
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          brightness: Brightness.dark,
          title: Text('Inventory System'),
        ),
        body: Center(
          child: Text('WIP'),
        ),
      ),
    );
  }
}
