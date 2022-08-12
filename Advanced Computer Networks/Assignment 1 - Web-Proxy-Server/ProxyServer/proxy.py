from functools import cache
import socket
import threading
from urllib import request  #
import requests    
import time  

#Program is a threaded server, can handle multiple requests
#Program can also handle websocket connections
#Snippets of code altered from https://www.geeksforgeeks.org/creating-a-proxy-webserver-in-python-set-1/

port = 9097 
host = '127.0.0.1' 

cache = {}      #collection of cached urls

def main():     #main takes user input and sends the code to the designated functions accordingly
    start = int(input('Press 1 to create a connection, press 2 to check if a url is blocked, press 3 to block a url, press 4 to cache a url or 5 to exit'))
    if(start == 1):
            create_socket() 
    elif(start == 2):
        blocked = (input('Enter the url you want to check'))
        blockedUrls(blocked)
    elif(start == 3):
        blocked = (input('Enter the url you want to block'))
        blockUrl(blocked)
    elif(start == 4):
        cacheUrl = (input('Enter the url you want to cache'))
        cacheCheck(cacheUrl)
    elif(start ==5):
        print("Thank you for joining our server! See you next time")
        exit()
    else:
        ("A fatal error has occured, please restart the program.")


def create_socket():
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM) #Next create a socket object
    s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1) #Re-use the socket        
    print ("Socket created successfully")       #socket creation
    
    s.bind((host, port))                         #Bind to the assigned port      
    print ("Socket is bound to %s" %(port))     
    s.listen(10)                                #put the socket into listening mode
    print ("Socket is listening...")  
             
    while True:                                 #a forever loop until we interrupt it or an error occurs
        connection, addr = s.accept()           #Establish connection with client
        print ('Got connection from', addr)
        threadListener = threading.Thread(target = requestRcvd(s,connection), args=(connection,addr)) #Ready to create a new thread for any request made and run requestReceived
        threadListener.daemon = True
        threadListener.start()                  #start threads

def getUrl(request):
        first_line = str(request).split('\n')[0] #parse the first line
        url = first_line.split(' ')[1]           #get url
        return url

def getPort(url):
        http_pos = url.find("://")              #find pos of ://
        if(http_pos == -1):
            temp=url

        else:
            temp = url[(http_pos+3):]           #get the rest of url

        port_pos = temp.find(":")               #find the port pos (if any)
        webserver_pos = temp.find("/")          #find end of web server
        if webserver_pos == -1:
            webserver_pos = len(temp)

        webserver = ""
        port = -1
        if(port_pos==-1 or webserver_pos < port_pos):

            port = 9097  #default port
            webserver = temp[:webserver_pos]

        else: #specific port
            port = int((temp[(port_pos+1):])[:webserver_pos-port_pos-1])
            webserver = temp[:port_pos]
        return port

def blockedUrls(blocked):   #checking if a url is blocked in txt document
    print("Checking if the url entered is blocked")
    lines = open("blockedUrls.txt").read().splitlines()         #opens reads the txt document blockedUrls
    for line in lines:                                      #for loop locating if a url is in the txt document print URL is already blocked
        if (str(blocked) in (lines)):
            print("Url is already blocked")
        else: 
            print("Url is not blocked")                     #else print that URL is not blocked

    main()

def blockUrl(blocked):                      #function to block a specified url                
    lines = [blocked]                       #blocked being the inputted url from user
    with open('blockedUrls.txt', 'a') as f:             #'a' meaning appending to blockedUrl txt document
        for line in lines:
            f.write(line)                               #writing the new url to the txt doc
            f.write('\n')
    main()

def getCache(url):                          #Function to get the cache 
    print("Fetching cache from server...")
    response = requests.get(url)
    return response.text

def cacheCheck(url):                        #Function to add a URL to the cache if it is not already present
    if url not in cache:
        cache[url] = getCache(url)
        print('URL has been added to the cache')
        print(cache[url])
        main()

def requestRcvd(s,connection):              #socket conncting to the browser - data to be sent to and from browser.
    print("Thank you for connecting")
    s = socket.socket()                     #socket creation
    request = connection.recv(1024)

    url = getUrl(request)    #Find url
    print(str(url))
    port = getPort(url)         #getting port number

    print("Request for website: " + url + " at Port: " + str(port))

    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
    s.connect(('127.0.0.1', port))
    s.sendall(request)
    while 1:
        print("You've made it")
    
        data = connection.recv(1024)
        #data = cacheCheck(url)
        print(str(data))
        if (len(data) > 0):
            s.send(data)        #Send to browser
            print("Working")
         #   main()
        else:
            break

if __name__ == "__main__":
    main()
