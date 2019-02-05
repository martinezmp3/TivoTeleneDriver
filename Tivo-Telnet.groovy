/********
/********TiV0 Telnet Control
/***************************
/*
ACTION_A
ACTION_B
ACTION_C
ACTION_D

VIDEO_ON_DEMAND
STOP
CLEAR
NUM0
NUM1
NUM2
NUM3
NUM4
NUM5
NUM6
NUM7
NUM8
NUM9
PLAY 
FORWARD
REVERSE
PAUSE 
SLOW 
REPLAY 
ADVANCE 
RECORD


UP
DOWN
LEFT
RIGHT
SELECT
TIVO 
LIVETV 
GUIDE
INFO 
EXIT 

capability "TV"
channelDown()
channelUp()

capability "MusicPlayer"
pause()
play()
stop()
*/

metadata 
{
	definition (name: "Tivo Telnet", namespace: "jorge.martinez", author: "Jorge Martinez") 
	{
		capability "Sensor"
		capability "Polling"
		capability "Telnet"
		capability "Initialize"
//		capability "MusicPlayer"
		capability "TV"
		attribute "channel", "NUMBER"
		command "chUp"
		command "chDown"
		command "pause"
		command "myShows"
		command "liveTv"
		command "play"
		command "up"
		command "down"
		command "left"
		command "right"
		command "TiV0"
		command "Netflix"
		command "select"
		command "standby"
		command "clear"
		command "stop"
		command "guide"
		command "testCommand", ["STRING"]
		command "setCH", ["STRING"]
		command "nextTrack"
		command "previousTrack"
	}

	preferences 
	{
		section("Device Settings:") 
		{
			input "TiVoIP", "string", title:"TiVoIP", description: "", required: true, displayDuringSetup: true
			input "TiVoMini", "bool", title:"", description: "Is this a tivo mini", required: true, displayDuringSetup: true
		}
	}
}

def guide (){
	sendMsg("IRCODE GUIDE")
}

def testCommand(Command){
	sendMsg(Command)
//	sendMsg("IRCODE "+Command)
}
def send1 ()
{
	sendMsg("IRCODE NUM1")
}
def send4 ()
{
	sendMsg("IRCODE NUM4")
}
def setCH (CH){
	if (settings.TiVoMini){ //if is mark as mini
		String[] str
		str = CH.split("")
		pause (2000)
		for(String values : str ){
			send4 ()
//			sendMsg("IRCODE NUM${values}")
//			pause (2000)
		}
	}
	else{ //if is not a mini
		sendMsg("SETCH "+ CH)
	}

	
//	log.debug(values);
//	sendMsg("SETCH " + CH)
}
def clear(){
	sendMsg("IRCODE CLEAR")
}

def standby(){
	sendMsg("IRCODE STANDBY")
}
def select(){
	sendMsg("IRCODE SELECT")
}
def Netflix(){
	sendMsg("IRCODE NETFLIX")
}

def TiV0 (){
	sendMsg("IRCODE TIVO")
}
def up (){
	sendMsg("IRCODE UP")
}
def down (){
	sendMsg("IRCODE DOWN")
}
def left (){
	sendMsg("IRCODE LEFT")
}
def right (){
	sendMsg("IRCODE RIGHT")
}
def play (){
	sendMsg("IRCODE PLAY")
}
def liveTv(){
	sendMsg("IRCODE LIVETV")
}
def chUp (){
	sendMsg("IRCODE CHANNELUP")
}
def nextTrack(){
	chUp ()
}
def chDown (){
	sendMsg("IRCODE CHANNELDOWN")
}
def previousTrack(){
	chDown ()
}
def myShows (){
	sendMsg("IRCODE NOWSHOWING")
}

def pause (){
	sendMsg("IRCODE PAUSE")
}
def stop(){
	sendMsg("IRCODE STOP")
}
def installed() 
{
	log.info('Tivo Telnet : installed()')
	initialize()
}

def updated() 
{
	log.info('Tivo Telnet: updated()')
	initialize()
}
def initialize() 
{
	log.info('Tivo Telnet: initialize()')
	telnetClose() 
	log.info ("TiVo IP ${settings.TiVoIP}")
	telnetConnect([termChars:[13]], settings.TiVoIP, 31339, settings.username, settings.password)
}

def sendMsg(String msg) 
{
	log.info("Sending telnet msg: " + msg)
	return new hubitat.device.HubAction(msg, hubitat.device.Protocol.TELNET)
}


private parse(String msg) 
{
	log.debug("Parse: " + msg)
	if(msg.startsWith("CH_STATUS"))
	{
		log.info "got channel update " + msg.substring(10,14)
		state.channel = msg.substring(10,14).toInteger()
		sendEvent(name: "channel", value: state.channel, isStateChange: true)
	}

}

def telnetStatus(String status){
	log.warn "telnetStatus: error: " + status
	if (status != "receive error: Stream is closed")
	{
		log.error "Connection was dropped."
		initialize()
	} 
}
def pause(millis) {
   def passed = 0
   def now = new Date().time
   log.debug "pausing... at Now: $now"
   /* This loop is an impolite busywait. We need to be given a true sleep() method, please. */
   while ( passed < millis ) {
       passed = new Date().time - now
   }
//   log.debug "... DONE pausing."
}
