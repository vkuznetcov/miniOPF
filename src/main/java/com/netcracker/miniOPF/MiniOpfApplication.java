package com.netcracker.miniOPF;

import com.netcracker.miniOPF.utils.repos.ServiceRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MiniOpfApplication
{
    public static void main(String[] args) throws IOException
    {
        SpringApplication.run(MiniOpfApplication.class, args);
    }
}
