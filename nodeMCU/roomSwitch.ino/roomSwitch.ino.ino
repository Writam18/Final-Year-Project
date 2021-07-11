
#include "ThingSpeak.h"
#include <ESP8266WiFi.h>
#include <math.h>

const char *ssid =  "Subhajyoti";     // replace with your wifi ssid and wpa2 key
const char *pass =  "917501205356";
int room[11][8] = { {0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0},
};
long prevValue = 0;
bool activeStatus = true;
WiFiClient  client;

//---------Channel Details---------//
unsigned long counterChannelNumber = 1270994;            // Channel ID
const char * myCounterReadAPIKey = "BO5WG0XEJ37JQI5C"; // Read API Key
const int FieldNumber1 = 1;  // The field you wish to read
//const int FieldNumber2 = 2;  // The field you wish to read
//-------------------------------//

void setup()
{
  Serial.begin(115200);
  WiFi.mode(WIFI_STA);
  ThingSpeak.begin(client);
  pinMode(LED_BUILTIN, OUTPUT);
  
}

void loop()
{
  //----------------- Network -----------------//
  if (WiFi.status() != WL_CONNECTED)
  {
    Serial.print("Connecting to ");
    Serial.print(ssid);
    Serial.println(" ....");
    while (WiFi.status() != WL_CONNECTED)
    {
      WiFi.begin(ssid, pass);
      delay(5000);
    }
    Serial.println("Connected to Wi-Fi Succesfully.");
  }
  //--------- End of Network connection--------//


  //---------------- Channel 1 ----------------//
  long value = ThingSpeak.readLongField(counterChannelNumber, FieldNumber1, myCounterReadAPIKey);
  long statusCode = ThingSpeak.getLastReadStatus();
  //------------Channel 1 value received------//

  
  if (statusCode == 200)
  {
    Serial.print("value: ");
    Serial.println(value);
    
    long rooms = value>>9;
    long onoff = value%2;
    long switches = value % (long)pow(2,9);
    switches = switches>>1;                       //exclude the onoff bit
    
    int i = 10;                                   //for keeping track of the room
    while(rooms>0){
      if(rooms%2 == 1){
        
        long temp = switches;
        int j = 7;                                //for keeping track of the switch
        while(temp>0){
          if(temp%2 == 1){
            room[i][j] = onoff;
          }
          temp = temp/2;
          j--;
        }
      }
      rooms = rooms/2;
      i--;
    }
//    Serial.println(room[0][0]);
    if(prevValue != value){
      for(i = 0; i<11; i++){
        Serial.print("[ ");
        for(int j=0; j<8; j++){
          Serial.print(room[i][j]);
          Serial.print(" ");
        }
        Serial.println("]");
      }
      prevValue = value;
    }
  }
  else
  {
    Serial.println("Unable to read channel / No internet connection");
  }
  activeStatus = !activeStatus;
  digitalWrite(LED_BUILTIN, activeStatus);
  delay(3000);
  //-------------- End of Channel 1 -------------//

//  //---------------- Channel 2 ----------------//
//  long humidity = ThingSpeak.readLongField(counterChannelNumber, FieldNumber2, myCounterReadAPIKey);
//  statusCode = ThingSpeak.getLastReadStatus();
//  if (statusCode == 200)
//  {
//    Serial.print("Humidity: ");
//    Serial.println(humidity);
//  }
//  else
//  {
//    Serial.println("Unable to read channel / No internet connection");
//  }
//  delay(100);
//  //-------------- End of Channel 2 -------------//
}
