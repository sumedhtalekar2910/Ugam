package com.ugams.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "ugams - Fetch Api Configuration"
)
public @interface FetchApiConfig {

    @AttributeDefinition(
            name = "Url for Single User",
            type = AttributeType.STRING)
    public String SingleUserUrl() default "https://reqres.in/api/users/";

    @AttributeDefinition(
            name = "Url for List of User",
            type = AttributeType.STRING)
    public String MultiUserUrl() default "https://reqres.in/api/users?page=";
}
