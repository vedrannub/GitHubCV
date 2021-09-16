using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplicationStudentsMVC.Models
{
    public class Professor
    {
        public int Id { get; set; }

        public string Name { get; set; }

        public List<StudentSubject> StudentSubjects { get; set; } = new List<StudentSubject>();

        public List<Subject> Subjects { get; set; } = new List<Subject>();

        public int UserId { get; set; }

        public User User { get; set; }
    }
}
