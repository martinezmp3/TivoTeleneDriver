Driver for Hubitat Elevation Hub
(This is a work in progress any feedback will be really appreciate)
What you need:
•	A Hubitat Hub
•	A Tivo Box I’m not sure yet what version of tivo work
•	You need to allow network remote control on your tivo (Menu -> Settings -> Remote & Devices -> Network remote control)
•	You need to know your tivo IP Address (Menu -> Settings -> Network Settings
•	Open up the Tivo-Telnet.groovy and copy Ctrl+a and Ctrl+c (https://raw.githubusercontent.com/martinezmp3/TivoTeleneDriver/master/Tivo-Telnet.groovy)
•	In your hub web page, select the "Drivers Code" section and then click the "+ New Driver" button
•	Click in the editor window. Then PASTE all of the code you copied in the previous step.
•	Click the SAVE button in the editor window
•	In the hubitat web page, select the "Devices" section, and then click the "Add Virtual Device" button in the top right corner.
•	In the window that appears, please fill in the "Device Name", "Device Label", and "Device Network Id" fields. Make sure the Device Network Id field is UNIQUE! Could be the same that the device name or label but HAVE TO BE UNIQUE no other device in you hub could have the same Network 
•	In the type filed look for Tivo Telnet
•	Click save Device
•	In the Preferences section ip = ip of Tivo 
•	Click save
•	If everything went well you should be able to control the TiVo now 

(this is a work in progress any feedback will be really appreciate)
