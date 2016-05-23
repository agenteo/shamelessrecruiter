package shamelessrecruiter.web;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterRepository extends MongoRepository<Recruiter, String> {
    public List<Recruiter> findByEmail(String email);
}