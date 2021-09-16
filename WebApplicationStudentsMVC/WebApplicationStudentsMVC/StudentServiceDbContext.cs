using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplicationStudentsMVC.Models;

namespace WebApplicationStudentsMVC
{
    public class StudentServiceDbContext : DbContext
    {

        public StudentServiceDbContext(DbContextOptions context) : base(context)
        {

        }
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<StudentSubject>().HasOne(x => x.Professor).WithMany(x => x.StudentSubjects).OnDelete(DeleteBehavior.NoAction).IsRequired(false);

            modelBuilder.Entity<StudentSubject>().HasOne(x => x.Student).WithMany(x => x.StudentSubjects).OnDelete(DeleteBehavior.NoAction).IsRequired(false);

            modelBuilder.Entity<StudentSubject>().HasOne(x => x.Subject).WithMany(x => x.StudentSubjects).OnDelete(DeleteBehavior.NoAction).IsRequired(false);

            modelBuilder.Entity<User>().HasOne(x => x.Professor).WithOne(x => x.User).HasForeignKey<User>(x => x.ProfessorId).IsRequired(false);

            modelBuilder.Entity<User>().HasOne(x => x.Student).WithOne(x => x.User).HasForeignKey<User>(x => x.StudentId).IsRequired(false);

            modelBuilder.Entity<Student>().HasOne(x => x.User).WithOne(x => x.Student).HasForeignKey<Student>(x => x.UserId);

            modelBuilder.Entity<Professor>().HasOne(x => x.User).WithOne(x => x.Professor).HasForeignKey<Professor>(x => x.UserId);

            modelBuilder.Entity<Subject>().HasOne(x => x.Professor).WithMany(x => x.Subjects).IsRequired(false);

            modelBuilder.Entity<User>().HasData(GetUsers());

            modelBuilder.Entity<Student>().HasData(GetStudents());

            modelBuilder.Entity<Professor>().HasData(GetProfessors());

            modelBuilder.Entity<Subject>().HasData(GetSubjects());
        }

        //entities
        public DbSet<Student> Students { get; set; }

        public DbSet<Professor> Professors { get; set; }

        public DbSet<Subject> Subjects { get; set; }

        public DbSet<StudentSubject> StudentSubjects { get; set; }

        public DbSet<User> Users { get; set; }

        public object[] GetStudents()
        {

            object[] data = new object[] {

                new Student()
                {
                    Id = 1,
                    Name = "Mirko",
                    UserId=1
                },
                new Student()
                {
                    Id = 2,
                    Name = "Maja",
                    UserId=4
                },
                new Student()
                {
                    Id = 3,
                    Name = "Petar",
                    UserId=5
                },
                new Student()
                {
                    Id = 4,
                    Name = "Kendrick",
                    UserId=6
                }
            };

            return data;
        }

        public object[] GetProfessors()
        {

            object[] data = new object[] {

                new Professor()
                {
                    Id = 1,
                    Name = "Dejan",
                    UserId=2
                },
                new Professor()
                {
                    Id = 2,
                    Name = "Tomce",
                    UserId=7
                },
                new Professor()
                {
                    Id = 3,
                    Name = "Slobodan",
                    UserId=8
                },
                new Professor()
                {
                    Id = 4,
                    Name = "Petre",
                    UserId=9
                },
            };

            return data;
        }

        public object[] GetSubjects()
        {

            object[] data = new object[]
            {
                new Subject()
                {
                    Id=1,
                    Name="OOP"

                },
                new Subject()
                {
                    Id=2,
                    Name="SNZ"

                },
                new Subject()
                {
                    Id=3,
                    Name="DM2"

                },
                new Subject()
                {
                    Id=4,
                    Name="DM1"

                },
                new Subject()
                {
                    Id=5,
                    Name="VIS"

                },
                new Subject()
                {
                    Id=6,
                    Name="K1"

                },new Subject()
                {
                    Id=7,
                    Name="OVD"

                },
                new Subject()
                {
                    Id=8,
                    Name="KMS"

                },
                new Subject()
                {
                    Id=9,
                    Name="KM"
                },

            };

            return data;
        }

        public object[] GetUsers()
        {

            object[] data = new object[] {

                new User()
                {
                    Id=1,
                    Password="password",
                    Username="mirko",
                    StudentId=1,
                    Role="student"
                },
                new User()
                {
                    Id=2,
                    Password="password",
                    Username="dejan",
                    ProfessorId=1,
                    Role="professor"
                },
                new User()
                {
                    Id=3,
                    Password="password",
                    Username="studentservice",
                    Role="studentservice"
                },
                new User()
                {
                    Id=4,
                    Password="password",
                    Username="maja",
                    StudentId = 2,
                    Role="student",
                },
                new User()
                {
                    Id=5,
                    Password="password",
                    Username="petar",
                    StudentId =3,
                    Role="student"
                },
                new User()
                {
                    Id=6,
                    Password="password",
                    Username="kendrick",
                    StudentId=4,
                    Role="student"
                },
                new User()
                {
                    Id=7,
                    Password="password",
                    Username="tomce",
                    ProfessorId=2,
                    Role="professor"
                },
                new User()
                {
                    Id=8,
                    Password="password",
                    Username="slobodan",
                    ProfessorId=3,
                    Role="professor"
                },
                new User()
                {
                    Id=9,
                    Password="password",
                    Username="petre",
                    Role="professor",
                    ProfessorId=4,
                },


            };

            return data;
        }
    }
}
