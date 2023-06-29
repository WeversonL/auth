# auth

## About

Project created to practice Spring Security for generating and authenticating with JWT tokens.

## Technologies

- Java
- Spring Boot
- PostgreSQL
- Docker

**Spring Data JPA was chosen for this project, due to the simplicity required for the queries. There are no queries or complex insertions that demand high resources and performance, so JPA was used in this application**

## Requirements

1. [Docker](https://docs.docker.com/engine/install/)
2. [Docker-compose](https://docs.docker.com/compose/)

## Get Started

### Running the application with docker-compose

1. Clone the repository or download the source code

        git clone https://github.com/WeversonL/auth.git
        cd auth

2. Start with docker-compose

        docker-compose up -d

3. You can check the documentation with Swagger UI for examples on how to consume the API. Just go to your browser:

        http://localhost:8080/security/v1/swagger-ui/index.html#/

4. Some users have been preloaded for testing. One will have access to admin roles, another user and another user will
   be expired. Are they:

        ADMIN:
                username: admin-user
                password: strong@pass

        SIMPLE:
                username: simple-user
                password: strong@pass

        BLOCKED:
                username: blocked-user
                password: strong@pass  

4. Use the curl below for the request

        curl --location --request POST 'http://localhost:8080/security/v1/auth?username=${USERNAME}&password=${PASSWORD}'

## Other information

1. To terminate and destroy the containers, you can run

        docker-compose down

⚠️ Still in development

## License

`auth` is released under the [GNU General Public License, Version 2](LICENSE)

        Copyright (C) 2022 Weverson Lemos

        This program is free software; you can redistribute it and/or
        modify it under the terms of the GNU General Public License
        as published by the Free Software Foundation; either version 2
        of the License, or (at your option) any later version
