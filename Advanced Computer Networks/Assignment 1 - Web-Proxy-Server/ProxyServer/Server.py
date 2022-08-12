import signal
from socket import socket
import threading


#signal.signal(signal.SIGINT, self.shutdown)
#self.__clients = {}
#request = connection.recv(config['MAX_REQUEST_LEN'])
MAX_REQUEST_LEN = 8192

def main(self, port):
    self.serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM) #Creates a TCP socket
    self.serverSocket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1) #Re-use the socket
    self.serverSocket.bind(('', port))  #Bind the socket to a public host, and a port
    self.serverSocket.listen(10) #become a server socket
    while True:
            (proxySocket, proxy_address) = self.serverSocket.accept()  #Establish the connection
            listen = threading.Thread(target=self.getRequest, args=(proxySocket, proxy_address))
            listen.setDaemon(True)
            listen.start()


def __init__(self, port):
        self.serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM) #Creates a TCP socket
        self.serverSocket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1) #Re-use the socket
        self.serverSocket.bind(('', port))  #Bind the socket to a public host, and a port
        self.serverSocket.listen(10) #become a server socket
        while True:
            (proxySocket, proxy_address) = self.serverSocket.accept()  #Establish the connection
            listen = threading.Thread(target=self.getRequest, args=(proxySocket, proxy_address))
            listen.setDaemon(True)
            listen.start()

def getUrl(self, url, config):
        first_line = config.split('\n')[0] #parse the first line
        url = first_line.split(' ')[1]  #get url
        return url

def getPort(self, url):
        http_pos = url.find("://") #find pos of ://
        if(http_pos == -1):
            temp=url

        else:
            temp = url[(http_pos+3):] #get the rest of url

        port_pos = temp.find(":") #find the port pos (if any)
        webserver_pos = temp.find("/")  #find end of web server
        if webserver_pos == -1:
            webserver_pos = len(temp)

        webserver = ""
        port = -1
        if(port_pos==-1 or webserver_pos < port_pos):

            port = 80   #default port
            webserver = temp[:webserver_pos]

        else: #specific port
            port = int((temp[(port_pos+1):])[:webserver_pos-port_pos-1])
            webserver = temp[:port_pos]
        return port

def getWebServer(self, url):
        http_pos = url.find("://") #find pos of ://
        if(http_pos == -1):
            temp=url

        else:
            temp = url[(http_pos+3):] #get the rest of url

            port_pos = temp.find(":") #find the port pos (if any)

                    #find end of web server
            webserver_pos = temp.find("/")
        if webserver_pos == -1:
            webserver_pos = len(temp)

        webserver = ""
        port = -1
        if(port_pos==-1 or webserver_pos < port_pos):

            port = 80   #default port
            webserver = temp[:webserver_pos]

        else: #specific port
            port = int((temp[(port_pos+1):])[:webserver_pos-port_pos-1])
            webserver = temp[:port_pos]
        return webserver

def requestReceived(self, proxySocket, proxy_address):
        request = proxySocket.recv(MAX_REQUEST_LEN)

        url = self.getUrl(request) # find url
        webserver = self.getWebserver(request, url) # find webserver
        port = self.getPort(request, url) # find port
        print("Listening on port" + port)

if __name__ == '__main__':
    main()



       # s = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
        #s.settimeout(config['CONNECTION_TIMEOUT'])
        #s.connect((webserver, port))
        #s.sendall(request)
