package foo;

import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Api(name = "myApi",
     version = "v1",
     audiences = "1033985045867-2q1v90ob6p4rg6scihedmsj9cgiqt5cd.apps.googleusercontent.com",
  	 clientIds = {"1033985045867-2q1v90ob6p4rg6scihedmsj9cgiqt5cd.apps.googleusercontent.com",
        "927375242383-jm45ei76rdsfv7tmjv58tcsjjpvgkdje.apps.googleusercontent.com"},
     namespace =
     @ApiNamespace(
		   ownerDomain = "helloworld.example.com",
		   ownerName = "helloworld.example.com",
		   packagePath = "")
     )

public class PetitionEndpoint {

        @ApiMethod(name = "createPetition")
        public Entity createPetition(User user, String title, String description, String tags) {
        
            if (user == null) {
                return 510;
            }

            org.w3c.dom.Entity petitionEntity = new Entity(title + "Petition");
            petitionEntity.setProperty("title", title);
            petitionEntity.setProperty("description", description);
            petitionEntity.setProperty("tags", tags);
            petitionEntity.setPrefix("owner", user.getEmail());
    
            DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
            datastore.put(petitionEntity);
    
            return petitionEntity;
        }
    }
    