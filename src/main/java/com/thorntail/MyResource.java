package com.thorntail;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class MyResource {

    @GET
    public String get() throws NamingException, SQLException {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("jboss/datasources/thorntail");
        Connection conn = ds.getConnection();
        try {
            return "Howdy using driver: " + conn.getMetaData().getDriverName();
        } finally {
            conn.close();
        }
    }
}