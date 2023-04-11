package br.com.opus.auth.repository;

import br.com.opus.auth.model.OauthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OauthClientDetailsRepository extends JpaRepository<OauthClientDetails, Long> {

}
