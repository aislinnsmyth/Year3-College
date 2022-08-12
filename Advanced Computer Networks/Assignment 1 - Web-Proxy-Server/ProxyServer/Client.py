# Import socket module
import socket            
 
# Create a socket object
s = socket.socket()        
 
# Define the port on which you want to connect
port = 9097              
 
# connect to the server on local computer
s.connect(('127.0.0.1', port))
 
# receive data from the server and decoding to get the string.
print (s.recv(1024).decode())
# close the connection
s.close()    
     





#import socket
#import time

# creates socket object
#s = socket.socket()
#print("Socket created successfully")

#host = '127.0.0.1' 
#port = 12345
#s.connect((host, port))

#while True:
    # receive data from the server 
 #   print("Sending 'hello' message..")
  #  s.sendall(b'hello')
   # print(s.recv(1024))
    #time.sleep(2)