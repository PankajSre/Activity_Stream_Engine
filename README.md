# Activity or event stream engine

##  This is an event stream engine similar to pump.io.
1.	Core functionality – 
1.	Creates an activity or event stream for users based on their subscriptions to communities. At a fundamental level, every user has an inbox and an outbox. Messages are emitted from an outbox and the engine then determines all user inboxes that this message should become part of. 
2.	There are circles – groups of people that come together for any purpose. Once a circle is created, then this becomes a target for communication. Messages are sent within and even across circles.
3.	The stream of messages in a circle is the activity stream of that circle.
4.	Messages have tags. They can be filtered based on tags. Tags are system and user defined.
5.	Messages model actions on certain data types. They therefore have URLs for actions that they can carry. Example – see a blog post. This has URL to the blog post, and invokes a reader to see the blog post. This is software extensible. 
6.	The messages themselves are immutable, however messages can be replied to.
1.	Messages can be deleted in a person’s inbox but they don’t get deleted from the original activity stream 
2.	Stories:
1.	Front end functionality
1.	Authentication and sign-on
2.	Home - default activity stream = inbox
3.	Finding circles
4.	Create a circle
5.	Subscribe to circles
6.	Activity stream in a circle
7.	Seeing a message - message types will have their own visual templates
8.	Composing a message
9.	Triggering an action from a message
2.	API layer
1.	user auth
2.	create user, delete user, suspend user
3.	create circle, archive circle
4.	add user to circle, remove user from circle
5.	get circle enrolments
6.	get user inbox, with circles as argument
7.	subscribe user to stream with tags as an argument
3.	Business logic layer
1.	Inbox management / routing -> binding user’s inbox to a stream in a circle
2.	Multiplexing messages from across streams
4.	Persistence layer
1.	DB
1.	users
2.	circles
3.	streams 
2.	Redis based cache
3.	Design elements to focus on:
1.	Architecture components:
1.	Client side app connecting to a server-side set of Micro services using REST APIs and SocketIO for push 
2.	Polyglot persistence models with immutability of messages built at the persistence layer
3.	Micro service based separation of the persistence model into users, circles and messages. The cross cutting services are for routing and multiplexing. 
2.	client side: 
1.	component design - how pluggable and what is the unit of reuse.
2.	how did they model message. is there a class hierarchy on message and polymorphism that could be used to manage all types of messages
3.	is the client side binding to circles on the server side or to the concept of a single inbox. it should be inbox - binding to server side circles will mean an uncontrolled number of channels being opened
3.	server side:
1.	what is the data model for messages. is extensibility for new activity types taken care of by using the idea of named activities and a payload. are tags used for messages.
2.	How is immutability modeled? Did they use checksums etc. What about its enforcement at the data API? 
3.	what is the data model for the subscriptions of an inbox. how are circles and tags managed. is the equivalent of a subscription list created such that everything is reduced to a tag@circle expression
4.	Advanced concepts that can be introduced as variants: 
1.	Pluggable client side components to act as templates for various types of messages/activity.
2.	Use socketIO for updates in addition to REST or HATEAOS based APIs for bulk message streams, circles, people etc. 
3.	State management on the server to a multi-server environment. 
4.	Use Kafka on the server side as a message store
5.	Potential tech stacks
1.	Complete MEAN or MERN – Angular or React based front end – uses Material or SemanticUI, SocketIO and JSON as well as REST and JSON based communication with server. API layer written using NodeJS and Express. Middleware layer written as micro services in NodeJS. Persistence in MongoDB or Cassandra, cache as Redis. Can be extended to Kafka. 
2.	Java Spring + Angular/React – Angular or React based front-end  – uses Material or SemanticUI. Spring boot based micro services. Tomcat and Spring based API gateway. Persistence in Mongo/Cassandra or MySQL. Cache in Redis. Can be extended to Kafka
6.	Steps/Sprints
1.	Sprint 1: goals – model the UI, JSON data formats for communication (messages, users, circles, page config data) and the API layer. 
2.	Sprint 2: goals – build the API layer. Dummy the middleware services to return pre-formatted JSON. Inject these as dependencies into the API layer
3.	Sprint 3: goals – build the middleware tier. Solve the problems of routing and multi-plexing
4.	Sprint 4: goals – build the persistence model. Segregate into micro-services. 
5.	Sprint 5: goals - build the UI with a simple activity stream. Load data using a REST call. Post messages back into the stream and test end-end. Dummy the user session identifier for API calls.
6.	Sprint 6: goals – build the UI for user auth, circles, subscription and pass the user session dynamically. 

