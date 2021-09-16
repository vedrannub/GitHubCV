﻿// <auto-generated />
using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using WebApplicationStudentsMVC;

namespace WebApplicationStudentsMVC.Migrations
{
    [DbContext(typeof(StudentServiceDbContext))]
    [Migration("20210911152053_Migrate")]
    partial class Migrate
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("Relational:MaxIdentifierLength", 128)
                .HasAnnotation("ProductVersion", "5.0.9")
                .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

            modelBuilder.Entity("WebApplicationStudentsMVC.Models.Professor", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("Name")
                        .HasColumnType("nvarchar(max)");

                    b.Property<int>("UserId")
                        .HasColumnType("int");

                    b.HasKey("Id");

                    b.HasIndex("UserId")
                        .IsUnique();

                    b.ToTable("Professors");

                    b.HasData(
                        new
                        {
                            Id = 1,
                            Name = "Dejan",
                            UserId = 2
                        },
                        new
                        {
                            Id = 2,
                            Name = "Tomce",
                            UserId = 7
                        },
                        new
                        {
                            Id = 3,
                            Name = "Slobodan",
                            UserId = 8
                        },
                        new
                        {
                            Id = 4,
                            Name = "Petre",
                            UserId = 9
                        });
                });

            modelBuilder.Entity("WebApplicationStudentsMVC.Models.Student", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("Name")
                        .HasColumnType("nvarchar(max)");

                    b.Property<int>("UserId")
                        .HasColumnType("int");

                    b.HasKey("Id");

                    b.HasIndex("UserId")
                        .IsUnique();

                    b.ToTable("Students");

                    b.HasData(
                        new
                        {
                            Id = 1,
                            Name = "Mirko",
                            UserId = 1
                        },
                        new
                        {
                            Id = 2,
                            Name = "Maja",
                            UserId = 4
                        },
                        new
                        {
                            Id = 3,
                            Name = "Petar",
                            UserId = 5
                        },
                        new
                        {
                            Id = 4,
                            Name = "Kendrick",
                            UserId = 6
                        });
                });

            modelBuilder.Entity("WebApplicationStudentsMVC.Models.StudentSubject", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<int?>("Grade")
                        .HasColumnType("int");

                    b.Property<int?>("ProfessorId")
                        .HasColumnType("int");

                    b.Property<int>("StudentId")
                        .HasColumnType("int");

                    b.Property<int>("SubjectId")
                        .HasColumnType("int");

                    b.HasKey("Id");

                    b.HasIndex("ProfessorId");

                    b.HasIndex("StudentId");

                    b.HasIndex("SubjectId");

                    b.ToTable("StudentSubjects");
                });

            modelBuilder.Entity("WebApplicationStudentsMVC.Models.Subject", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("Name")
                        .HasColumnType("nvarchar(max)");

                    b.Property<int?>("ProfessorId")
                        .HasColumnType("int");

                    b.HasKey("Id");

                    b.HasIndex("ProfessorId");

                    b.ToTable("Subjects");

                    b.HasData(
                        new
                        {
                            Id = 1,
                            Name = "OOP"
                        },
                        new
                        {
                            Id = 2,
                            Name = "SNZ"
                        },
                        new
                        {
                            Id = 3,
                            Name = "DM2"
                        },
                        new
                        {
                            Id = 4,
                            Name = "DM1"
                        },
                        new
                        {
                            Id = 5,
                            Name = "VIS"
                        },
                        new
                        {
                            Id = 6,
                            Name = "K1"
                        },
                        new
                        {
                            Id = 7,
                            Name = "OVD"
                        },
                        new
                        {
                            Id = 8,
                            Name = "KMS"
                        },
                        new
                        {
                            Id = 9,
                            Name = "KM"
                        });
                });

            modelBuilder.Entity("WebApplicationStudentsMVC.Models.User", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("Password")
                        .HasColumnType("nvarchar(max)");

                    b.Property<int?>("ProfessorId")
                        .HasColumnType("int");

                    b.Property<string>("Role")
                        .HasColumnType("nvarchar(max)");

                    b.Property<int?>("StudentId")
                        .HasColumnType("int");

                    b.Property<string>("Username")
                        .HasColumnType("nvarchar(max)");

                    b.HasKey("Id");

                    b.ToTable("Users");

                    b.HasData(
                        new
                        {
                            Id = 1,
                            Password = "password",
                            Role = "student",
                            StudentId = 1,
                            Username = "student1"
                        },
                        new
                        {
                            Id = 2,
                            Password = "password",
                            ProfessorId = 1,
                            Role = "professor",
                            Username = "professor1"
                        },
                        new
                        {
                            Id = 3,
                            Password = "password",
                            Role = "studentservice",
                            Username = "studentservice"
                        },
                        new
                        {
                            Id = 4,
                            Password = "password",
                            Role = "student",
                            Username = "student2"
                        },
                        new
                        {
                            Id = 5,
                            Password = "password",
                            Role = "student",
                            Username = "student3"
                        },
                        new
                        {
                            Id = 6,
                            Password = "password",
                            Role = "student",
                            Username = "student4"
                        },
                        new
                        {
                            Id = 7,
                            Password = "password",
                            Role = "professor",
                            Username = "professor2"
                        },
                        new
                        {
                            Id = 8,
                            Password = "password",
                            Role = "professor",
                            Username = "professor3"
                        },
                        new
                        {
                            Id = 9,
                            Password = "password",
                            Role = "professor",
                            Username = "professor4"
                        });
                });

            modelBuilder.Entity("WebApplicationStudentsMVC.Models.Professor", b =>
                {
                    b.HasOne("WebApplicationStudentsMVC.Models.User", "User")
                        .WithOne("Professor")
                        .HasForeignKey("WebApplicationStudentsMVC.Models.Professor", "UserId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("User");
                });

            modelBuilder.Entity("WebApplicationStudentsMVC.Models.Student", b =>
                {
                    b.HasOne("WebApplicationStudentsMVC.Models.User", "User")
                        .WithOne("Student")
                        .HasForeignKey("WebApplicationStudentsMVC.Models.Student", "UserId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("User");
                });

            modelBuilder.Entity("WebApplicationStudentsMVC.Models.StudentSubject", b =>
                {
                    b.HasOne("WebApplicationStudentsMVC.Models.Professor", "Professor")
                        .WithMany("StudentSubjects")
                        .HasForeignKey("ProfessorId")
                        .OnDelete(DeleteBehavior.NoAction);

                    b.HasOne("WebApplicationStudentsMVC.Models.Student", "Student")
                        .WithMany("StudentSubjects")
                        .HasForeignKey("StudentId")
                        .OnDelete(DeleteBehavior.NoAction);

                    b.HasOne("WebApplicationStudentsMVC.Models.Subject", "Subject")
                        .WithMany("StudentSubjects")
                        .HasForeignKey("SubjectId")
                        .OnDelete(DeleteBehavior.NoAction);

                    b.Navigation("Professor");

                    b.Navigation("Student");

                    b.Navigation("Subject");
                });

            modelBuilder.Entity("WebApplicationStudentsMVC.Models.Subject", b =>
                {
                    b.HasOne("WebApplicationStudentsMVC.Models.Professor", "Professor")
                        .WithMany("Subjects")
                        .HasForeignKey("ProfessorId");

                    b.Navigation("Professor");
                });

            modelBuilder.Entity("WebApplicationStudentsMVC.Models.Professor", b =>
                {
                    b.Navigation("StudentSubjects");

                    b.Navigation("Subjects");
                });

            modelBuilder.Entity("WebApplicationStudentsMVC.Models.Student", b =>
                {
                    b.Navigation("StudentSubjects");
                });

            modelBuilder.Entity("WebApplicationStudentsMVC.Models.Subject", b =>
                {
                    b.Navigation("StudentSubjects");
                });

            modelBuilder.Entity("WebApplicationStudentsMVC.Models.User", b =>
                {
                    b.Navigation("Professor");

                    b.Navigation("Student");
                });
#pragma warning restore 612, 618
        }
    }
}
