package peaksoft.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.model.Instructor;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {


//    @Query("select new peaksoft.dto.response.InstructorResponse(i.instructorId, " +
//            "i.lastName,i.email,i.phoneNumber,i.specialization)from Instructor i")
//    List<InstructorResponse> getAllInstructors();


    @Query("select count(i) from Instructor i where i.firstName =?1")
    Long getCountOfInstructors(@Param("firstName") String firstName);


    @Query("select count (i) from Instructor i where i.company.companyName =?1")
    Long countInstructorsByCompanyName(@Param("companyName") String companyName);


}