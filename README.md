Driver for Hubitat Elevation Hub
(This is a work in progress any feedback will be really appreciate)

honor a quien honor merece:
honor to whom honor is due:
this driver was base on 
https://www.tivocommunity.com/community/index.php?threads/tivo-ui-control-via-telnet-no-hacking-required.392385/
by Omikron
and 
https://community.hubitat.com/t/trouble-with-telnet/6785
Jeremy Akers 
jeremy.akers

What you need:
-	A Hubitat Hub
-	A Tivo Box I’m not sure yet what version of tivo work (UPDATE: Series3 running software 9.1 and up)
-	You need to allow network remote control on your tivo (Menu -> Settings -> Remote & Devices -> Network remote control)
-	You need to know your tivo IP Address (Menu -> Settings -> Network Settings
-	Open up the Tivo-Telnet.groovy and copy Ctrl+a and Ctrl+c (https://raw.githubusercontent.com/martinezmp3/TivoTeleneDriver/master/Tivo-Telnet.groovy)
-	In your hub web page, select the "Drivers Code" section and then click the "+ New Driver" button
-	Click in the editor window. Then PASTE all of the code you copied in the previous step.
-	Click the SAVE button in the editor window
-	In the hubitat web page, select the "Devices" section, and then click the "Add Virtual Device" button in the top right corner.
-	In the window that appears, please fill in the "Device Name", "Device Label", and "Device Network Id" fields. Make sure the Device Network Id field is UNIQUE! Could be the same that the device name or label but HAVE TO BE UNIQUE no other device in you hub could have the same Network 
-	In the type filed look for Tivo Telnet
-	Click save Device
-	In the Preferences section ip = ip of Tivo 
- if that tivo is a mini mark is a mini (for the moment setchannel do not work on tivo mini)
-	Click save
-	If everything went well you should be able to control the TiVo now 

(this is a work in progress any feedback will be really appreciate)
