package com.tramalho.rest.spring.boot.auth.service

import com.tramalho.rest.spring.boot.auth.model.UserDetailsImp
import com.tramalho.rest.spring.boot.auth.repository.UsersRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.Optional


@Service
class UserService(private val usersRepository: UsersRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetailsImp {

        val nullable = Optional.ofNullable(usersRepository.findByName(username))

        val users = nullable.orElseThrow {
            throw UsernameNotFoundException("No record with username $username")
        }

        return UserDetailsImp(users)
    }
}