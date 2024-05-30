package cl.restapi.retrievecountriesapi.repositories;

import cl.restapi.retrievecountriesapi.models.Country;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class CountryMongoRepositoryImpl implements CountryMongoRepository {
    private static final String CITIES = "cities";
    private final MongoTemplate mongoTemplate;

    public CountryMongoRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Country> findAllExcludeCities() {
        Query query = new Query();
        query.fields().exclude(CITIES);
        return mongoTemplate.find(query, Country.class);
    }

    @Override
    public List<Country> findCountriesRegionExcludeCities(String region) {
        Query query = new Query();
        region = region.trim();
        query.addCriteria(Criteria.where("region").regex(region, "i"));
        query.fields().exclude(CITIES);
        return mongoTemplate.find(query, Country.class);
    }

    @Override
    public List<Country> findCountriesSubregionExcludeCities(String subregion) {
        Query query = new Query();
        subregion = subregion.trim();
        query.addCriteria(Criteria.where("subregion").regex(subregion, "i"));
        query.fields().exclude(CITIES);
        return mongoTemplate.find(query, Country.class);
    }
}
