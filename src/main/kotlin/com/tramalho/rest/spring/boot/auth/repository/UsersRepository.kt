package com.tramalho.rest.spring.boot.auth.repository

import com.tramalho.rest.spring.boot.auth.model.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UsersRepository : JpaRepository<Users, Long> {

    @Query("SELECT u FROM User WHERE u.userName =:userName")
    fun findByName(@Param("userName") userName: String): Optional<Users>
}