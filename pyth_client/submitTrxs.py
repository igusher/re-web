import sys
import requests
import datetime

fileList = [sys.argv[i] for i in range(1,len(sys.argv))]
numOfTrxsInRequest = 1000
#url = "http://127.0.0.1:8080/motm/motm/re/submitTrxs"
url = "http://ec2-54-229-186-249.eu-west-1.compute.amazonaws.com:8080/motm/motm/re/submitTrxs"
headers = {"content-type":"text/plain"}
payload = "249-345;01199-01199;2013-05-03;000001;1522"

log = open("/home/user/redemptionengine/submitTrxs.log","w")
for trxFilePath in fileList:
	trxFile = open(trxFilePath, 'r');
	requestBody = ""
	successCount = 0;
	start = datetime.datetime.now();
	for trxId, trxLine in enumerate(trxFile):
		requestBody+= trxLine
		if (trxId % numOfTrxsInRequest) == 0:
			res = requests.post(url, data=requestBody, headers=headers);
			successCount += int(res.text);
			log.write(requestBody);
			requestBody =""
	if (requestBody != ""):
		res= requests.post(url,data=requestBody,headers=headers);
		successCount += int(res.text);
		log.write(requestBody);
		requestBody =""
	finish = datetime.datetime.now();
	print start,finish;
	print trxFilePath, successCount;
		

#r = requests.post(url,data=payload, headers=headers);
#print r.text;

print fileList;
