using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplicationStudentsMVC.Models
{
    public class Student
    {
        public int Id { get; set; }

        public string Name { get; set; }

        public List<StudentSubject> StudentSubjects { get; set; } = new List<StudentSubject>();

        public int UserId { get; set; }

        public User User { get; set; }


    }
}
