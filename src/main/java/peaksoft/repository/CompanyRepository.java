package peaksoft.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.model.Company;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
//    @Query("select new peaksoft.dto.response.CompanyResponse(c.companyId,c.companyName,c.locatedCountry) from Company c")
//    List<CompanyResponse> getAllCompanies();
    @Query("select c from Company  c where upper(c.companyName)like concat('%',:text,'%') " +
            "or upper(c.locatedCountry)like concat('%',:text,'%') ")
    List<Company> searchCompanyByCompanyName(@Param("text") String text, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "delete c from Company c where c.companyId = :companyId" )
    void deleteCompany(Long companyId);
}