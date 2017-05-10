# Android Phone Caller

A simple app that when a SMS is received from a specified number, it will call a different number

The idea came from management company restricting gate access to only 4 gsm numbers. This allows more numbers to re-use a phone by sending a text message to the phone which in turn triggers a call to open the gate. 

Its a quick a dirty app for a specific need, Improvements would be

- Better handling the permissions on startup 
- Removing the main activity and just having it run in the background
- Better handling of exceptions
- Better handle multiple numbers and changing of the target number in the app
- Some layer of persistence rather than hard coded numbers

Uses a broadcast receiver to wait for a SMS message from a specified number, if the message contains the magic words it starts a phonecall to open the gate

Built with Android studio, Marshmallow and on a Nexus 5 

