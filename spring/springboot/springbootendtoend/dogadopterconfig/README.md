# Using the config server

## Using a local git repo
Create and initiate a local git repo.
Add some properties files and commit those changes.

Assuming that the local git repo contains:
```bash
-rw-r--r--  1 carestra  staff   24 26 Mar 21:49 application.properties
-rw-r--r--  1 carestra  staff  128 26 Mar 21:49 datasource-dev.properties
-rw-r--r--  1 carestra  staff  127 26 Mar 21:08 datasource.properties
```

Access it via localhost; http://localhost:8888/<service-name>/<profile>
For example: http://localhost:8888/dog-adopter-config/default
displays
```json
    {
      "name": "dog-adopter-config",
      "profiles": [
        "default"
      ],
      "label": null,
      "version": "1d39f0c68a16c78ce4e55ef33cd1073d4914ebbb",
      "state": "",
      "propertySources": [
        {
          "name": "/Users/carestra/_temp/repo/dog-adopter-config/application.properties",
          "source": {
            "my.message": "Hello World!"
          }
        }
      ]
    }
```
For example: http://localhost:8888/datasource/dev
displays
```json
    {
      "name": "datasource",
      "profiles": [
        "dev"
      ],
      "label": null,
      "version": "1d39f0c68a16c78ce4e55ef33cd1073d4914ebbb",
      "state": "",
      "propertySources": [
        {
          "name": "/Users/carestra/_temp/repo/dog-adopter-config/datasource-dev.properties",
          "source": {
            "spring.datasource.username": "postgres",
            "spring.datasource.password": "example"
          }
        },
        {
          "name": "/Users/carestra/_temp/repo/dog-adopter-config/datasource.properties",
          "source": {
            "spring.datasource.url": "jdbc:postgresql://localhost:5432/",
            "spring.datasource.username": "postgres",
            "spring.datasource.password": "example"
          }
        },
        {
          "name": "/Users/carestra/_temp/repo/dog-adopter-config/application.properties",
          "source": {
            "my.message": "Hello World!"
          }
        }
      ]
    }
```