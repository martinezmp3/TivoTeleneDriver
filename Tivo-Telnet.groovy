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


*/

metadata 
{
	definition (name: "Tivo Telnet", namespace: "jorge.martinez", author: "Jorge Martinez") 
	{
		capability "Sensor"
		capability "Polling"
		capability "Telnet"
		capability "Initialize"
		attribute "channelStatus", "NUMBER"
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
		command "testCommand", ["STRING"]
		command "setCH", ["STRING"]
	}

	preferences 
	{
		section("Device Settings:") 
		{
			input "TiVoIP", "string", title:"TiVoIP", description: "", required: true, displayDuringSetup: true
		}
	}
}

def testCommand(Command){
	sendMsg(Command)
//	sendMsg("IRCODE "+Command)
}
def setCH (CH){
	String[] str;
	str = CH.split("");
	for(String values : str ){
		sendMsg("IRCODE NUM${values}")
//		sleep(3000)
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
def chDown (){
	sendMsg("IRCODE CHANNELDOWN")
}

def myShows (){
	sendMsg("IRCODE NOWSHOWING")
}

def pause (){
	sendMsg("IRCODE PAUSE")
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
		state.channelStatus = msg.substring(10,14).toInteger()
		sendEvent(name: "channelStatus", value: state.channelStatus, isStateChange: true)
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
