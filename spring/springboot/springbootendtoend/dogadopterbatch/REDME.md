# Batch server

## Possible errors
If 'batch_*' tables doe snot exist in db check the property below has been set correctly:
```
    spring.batch.jdbc.initialize-schema=always
```

This property should been read from the config server since this server is a config client.