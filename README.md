# The Apache Camel Experience 

Inspired by the holidays it seems to be a great time for a christmas themed blogpost for all you Java-lovers out there. No Santa's not here, but you will read about the camels on which the three wise men road to Bethlehem. Well... sort of. It's actually about Apache Camel and it's handy features for moving files.

You might think, why is he at the start of 2022 still writing about moving files. Newsflash, sftp's are still used a lot and their use might continue for years. Spring Users will argue for other libraries or even use the SFTP adapters, but I personally like Apache and as such have been using it quite a lot this past year. This is more of a short introduction, but there is a lot of [documentation on the subject](https://camel.apache.org/). The code for my project can be found on [our github-account](https://github.com/vangorpdirk/apachecamel) 

## Dependencies 

Since there are a lot of camel-dependencies, let me start by writing down the ones I use frequently. As a Spring Boot user who only needs his camel for moving files from and to a filesystem, these are my most common dependencies.

    <dependency>
        <groupId>org.apache.camel</groupId>
        <artifactId>camel-spring-boot-starter</artifactId>
        <version>${camel.version}</version>
    </dependency>
    <dependency>
        <groupId>org.apache.camel</groupId>
        <artifactId>camel-ftp</artifactId>
        <version>${camel.version}</version>
    </dependency>
    <dependency>
        <groupId>org.apache.camel</groupId>
        <artifactId>camel-swagger-java-starter</artifactId>
        <version>${camel.version}</version>
    </dependency>

The last library, Camel Swagger, is used for exposing REST services and their API, but in my example the included commons library is used to count a string. This is a great example of easy-to-use extra functionality in the Apache libraries. I do like the extra features of Apache. For example PGP encryption and decryption. Encryption is a common practice when moving files from one system to another. Usually it takes complicated code or something like Bouncy Castle to encrypt PGP-files, but with Apache Camel it's almost childsplay. You need to import the camel-crypto library for this extra. 

## SFTP 

As a developer you might wanna try this demo on your own system or maybe you'll feel more comfortable using a local SFTP for dev-work. An easy way to setup a free local sftp, is docker.

I'm not going to explain the entire process, but simply forward you to [the tutorial I used for setting up a dockerized SFTP](https://itnext.io/using-a-local-sftp-server-for-testing-659daaa0290a). Thanks to Tobias Wissmueller's short and clear tutorial, you'll setup a dockerized SFTP in under a few minutes. 

You could also use your own filesystem, no sftp needed, of which I'll include a very basic example at the end. 

## Get your camel on 

Basically you'll need to create a route to start the process. Create a class annotated with @Component and extend this class with the Routebuilder. In the obligated configure-method you can start forming your path. I personally use the application.properties to setup the paths, so it's easy to change them without changing the code, but you can also declare routes inside the path. For this short demo I will not catch the exceptions altough it is advised to always do so. 

For my example I used one of the greatest moviescripts of all time, Pulp Fiction. My application polls a folder on the sftp every 5 seconds, gets the file and counts and logs the number of times the word 'Fuck' is used. Then it moves the used file to the out folder on the sftp-server. 

    sftp.client=sftp://{{sftp.host}}{{sftp.in.folder}}?{{sftp.login}}&useUserKnownHostsFile=false&disconnect=true
    sftp.server={{sftp.client}}&delay={{sftp.polling.delay}}&preMove=../{{sftp.processing.folder}}&moveFailed=../failed&maxMessagesPerPoll=30&delete=true
    sftp.out.server=sftp://{{sftp.host}}{{sftp.out.folder}}?{{sftp.login}}&useUserKnownHostsFile=false&disconnect=true

I know, you could google the result and therefor it's not necessarily a usefull script, but the basics are there. As for users who don't want to declare a local sftp, you could use the following paths to create a structure in your resources-folder. 

    data.location=C:\\<path to folder>\\apachecamelexperience\\src\\main\\resources\\inFolder
    sftp.server=file://{{data.location}}?delay={{sftp.polling.delay}}&moveFailed=../Failed&move=../Archive
    sftp.out.server=file://{{data.location}}

Keep in mind that this is only a short demo to get the proverbial juices flowing. In a true Apache Camel experience you have tons of extra features. But for now, know this: if you're looking for a handy library for sftp-polling in java, get your camel on.

