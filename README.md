# Tiny JIRA App #

### Overview ###
A simple App to track issues (stories/bugs) for a small team of developers.

This App has following entities : Story, Bug, Assignee/Developer, Sprint (1 week = 1 sprint : assumption)

Features: Assignee
* `GET /assignees`   fetches list of assignees 
* `POST /assignees`  create an assignee
* `DELETE /assignees/{assigneesId}`  delete an assignee.
	
Features: Story
* `GET /story` fetch stories
* `GET /story/{id}` fetch a story
* `POST /story`   create a story
* `PUT /story`	update story

Features: Bug
* `GET /bug` fetch bugs
* `GET /bug/{id}` fetch a bug
* `POST /bug`   create a bug
* `PUT /bug`	  update a bug

Feature : Sprint
* `GET /sprints` fetch all sprint details
* `GET /sprint/{id}` fetch one sprint detail

Feature : Others
* `GET /plan/stories` plan estimated stories for upcoming sprints/ weeks
* `GET /replan/stories` replan estimated stories for upcoming sprints/ weeks

sample input JSONs :

Assignee : `{"assigneeName":"Jack"}`

Story : ` {"title": "add Junit","description": "add Junit to automate test cases.","storyPoint": 5}`

Bug : `{"title":"APIs not secure","description":"API are accessible to everyone. Not authentication is present","priority":"MAJOR"}`

 
### Tools Used ###

Technologies used :
* SpringBoot Web ` Used to expose REST endpoint since Spring has excellent support for developing RESTful web services`
* SpringBoot Data JPA ` Used to implement data access layer based on jpa`
* H2 DB ` Used In memory database since its a test application`
* Maven ` tool to build project and manage project dependencies`

### Installation & Setup ###

1. Download and install [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
2. Download and install [Apache Maven 3.5.2](https://archive.apache.org/dist/maven/maven-3/3.5.2)


### Configuration & Start ###

All relevant application settings are located in the ``src\main\resources\application.yml`` file.

Code can be cloned from `https://github.com/sumitnair/tinyjira.git`

You can build and run the application using ``mvn spring-boot:run`` on command line after installing of Java and Maven.

Upon startup, application can be access via `http://localhost:8080` , alternatively it can also be access via `https://tinyjira.herokuapp.com/` 

### Deployments ###

This application is deployed using [Heroku](https://www.heroku.com) on `https://tinyjira.herokuapp.com/`

### Known improvements to be done ###

1. Security needs to be implemented on REST endpoints.
2. Logging needs to implemented.
3. External database needs to be used.
4. JUNIT test cases needs to be added to automate unit testing.

