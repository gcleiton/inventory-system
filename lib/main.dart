import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(InventorySystem());

const channel = "compscience.database.ifce.inventory_system";

class InventorySystem extends StatefulWidget {
  @override
  _InventorySystemState createState() => _InventorySystemState();
}

class _InventorySystemState extends State<InventorySystem> {
  String text;

  Future<void> testingMethod() async {
    final methodChannel = MethodChannel(channel);
    text = await methodChannel.invokeMethod('testingMethod');
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(primaryColor: Colors.red),
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          brightness: Brightness.dark,
          title: Text('Inventory System'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text(
                text ?? 'Unknown',
                style: Theme.of(context)
                    .textTheme
                    .headline6
                    .copyWith(color: Colors.black),
              ),
              SizedBox(height: 16),
              ElevatedButton(
                onPressed: testingMethod,
                child: Text('Call Java Method'),
                style: ElevatedButton.styleFrom(primary: Colors.red),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
