package com.tramalho.rest.spring.boot.auth.service

import com.tramalho.rest.spring.boot.auth.model.UserDetailsImp
import com.tramalho.rest.spring.boot.auth.repository.UsersRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class UserService(private val usersRepository: UsersRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {

        val users = usersRepository.findByName(username).orElseThrow {
            throw UsernameNotFoundException("No record with username $username")
        }

        return UserDetailsImp(users)
    }
}