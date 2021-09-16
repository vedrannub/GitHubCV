using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplicationStudentsMVC.Models
{
    public class User
    {   
        public int Id { get; set; }

        public string Username { get; set; }

        public string Password { get; set; }

        public string Role { get; set; }

        public int? ProfessorId { get; set; }

        public Professor Professor { get; set; }

        public int? StudentId { get; set; }

        public Student Student { get; set; }

        

    }
}
