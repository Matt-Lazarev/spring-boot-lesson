package com.lazarev.springbootlesson2.repository;

import com.lazarev.springbootlesson2.entity.Employee;
import com.lazarev.springbootlesson2.model.EmployeeAggregate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findAllByDepartment(String department);

    @Query("select e from Employee e where e.salary between :from and :to")
    List<Employee> findAllBySalaryBetween(Integer from, Integer to);

    @Query(
    """
        select new com.lazarev.springbootlesson2.model.EmployeeAggregate(e.department, avg(e.salary))
        from Employee e
        group by e.department
    """)
    List<EmployeeAggregate> findAllAverageByDepartment();

    @Modifying
    @Transactional
    @Query("delete from Employee e where e.id = :id")
    int deleteEmployeeById(Integer id);
}
