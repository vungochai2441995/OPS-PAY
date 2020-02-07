package ops.gateway.repository;

import ops.gateway.entities.RequestMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ApprovedDataRepository extends JpaRepository<RequestMaster,Long> {
    @Query(value = "SELECT * " +
                    "FROM request_master u " +
                    "WHERE u.ORIGINAL_TRANS_ID=? " +
                        "AND u.CHECKED_TIME LIKE ? " +
                        "AND u.STATUS= 'APPROVED'" +
                        "AND u.RESULT=? " +
                    "LIMIT ? " +
                    "OFFSET ?",nativeQuery = true)
    List<RequestMaster> searchApprovedData(String originalTransId, Date checkedTime, String result, Integer limit, Integer offset);
}
