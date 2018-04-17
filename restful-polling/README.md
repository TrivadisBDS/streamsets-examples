# REST Polling with StreamSets

This is the sample showing the custom offset EL functionality in use. It uses the HTTP Client origin to regularly poll a REST service. In order to only get the delta, an offset has to be passed. This offset management is not supported by the HTTP Client.

Currently the example shows the HTTP Client origin in mode polling, with an interval of 10 seconds. 

## How to run it

In order to run the sample, perform the following steps:

```
cd docker

export DOCKER_HOST_IP=nnn.nnn.nnn.nnn
docker-compose up -d
```

This starts the following services:
  
* Kafka (port 9092)
* Zookeeper (port 2181)
* Streamsets (port 18630)
* Localstack 
* Sample Rest Service (18080)

Create two buckets in S3 called product and customer

```
aws --endpoint-url=http://${DOCKER_HOST_IP}:4572 --region=us-east-1 s3 mb s3://product
aws --endpoint-url=http://${DOCKER_HOST_IP}:4572 --region=us-east-1 s3 mb s3://customer
```

Open the [StreamSets UI](http://localhost:18630)

Import the two data flow files: 

* `./streamsets/CustomerPoller.json`
* `./streamsets/ProductPoller.json`

When you run the pipeline, it will start at offset 0 and then every 10 seconds poll for new records, providing the offset of the last record of the previous batch. 

The offsets are stored in `/data/custom-offsets/<offset-key>`. There are two files:

* `offset.dat` 			- holds the commited offset
* `offset.dat.prep` 	- holds the prepared offset

You can clear these files, if you want to start from the beginning. 

The offset-key is defined once as a pipeline parameter.

## Open issues

* showcase pagination
* showcase partitioning the REST calls
* showcase starting data flow in a batch mode