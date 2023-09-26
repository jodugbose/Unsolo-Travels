package com.interswitch.Unsolorockets.respository;

import com.interswitch.Unsolorockets.models.Kyc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KYCRepository extends JpaRepository<Kyc, Long> {
    Optional<Kyc> findByNin(String nin);
}
