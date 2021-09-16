using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplicationStudentsMVC.Models;

namespace WebApplicationStudentsMVC.Controllers
{
    public class ProfessorController : Controller
    {
        private readonly StudentServiceDbContext _dbContext;

        public ProfessorController(StudentServiceDbContext dbContext)
        {

            _dbContext = dbContext;

        }

        public IActionResult SelectSubject(string selectedSubjectId, string loggedInProfessorId)
        {
            var subjectStudents = _dbContext.StudentSubjects
                .Where(x => x.ProfessorId == int.Parse(loggedInProfessorId) &&
                x.SubjectId == int.Parse(selectedSubjectId)).Select(x => new StudentSubjectViewModel
                {
                    Name = x.Student.Name,
                    Grade = x.Grade
                }).ToList();

            var viewModel = new ProfessorViewModel
            {
                LoggedInProfessorId = loggedInProfessorId,
                ProfessorSubjects = _dbContext.Subjects.Where(x => x.ProfessorId == int.Parse(loggedInProfessorId)).ToList(),
                SelectedSubjectId = selectedSubjectId,
                SubjectStudents = subjectStudents,
                StudentsForAddingGrade = _dbContext.StudentSubjects.Include(x => x.Student)
                .Where(x => x.ProfessorId == int.Parse(loggedInProfessorId) &&
                x.SubjectId == int.Parse(selectedSubjectId)).Select(x => x.Student).ToList()
            };

            return View("Views/UserViews/ProfessorView.cshtml", viewModel);
        }

        public IActionResult AddGradeToStudent(string selectedStudentsGradeToSave, string selectedStudentsForAddingGradeId,
            string loggedInProfessorId, string selectedSubjectId)
        {

            var studentSubject = _dbContext.StudentSubjects.FirstOrDefault(x => x.ProfessorId == int.Parse(loggedInProfessorId)
            && x.StudentId == int.Parse(selectedStudentsForAddingGradeId) && x.SubjectId == int.Parse(selectedSubjectId));

            if(studentSubject == null)
            {
                studentSubject = new StudentSubject
                {
                    ProfessorId = int.Parse(loggedInProfessorId),
                    StudentId = int.Parse(selectedStudentsForAddingGradeId),
                    SubjectId = int.Parse(selectedSubjectId),
                    Grade = int.Parse(selectedStudentsGradeToSave)
                };
                _dbContext.Add(studentSubject);
            }
            else
            {
                studentSubject.Grade = int.Parse(selectedStudentsGradeToSave);
                _dbContext.Update(studentSubject);
            }

            _dbContext.SaveChanges();

            var viewModel = new ProfessorViewModel
            {
                LoggedInProfessorId = loggedInProfessorId,
                ProfessorSubjects = _dbContext.Subjects.Where(x => x.ProfessorId == int.Parse(loggedInProfessorId)).ToList(),
                SelectedSubjectId = selectedSubjectId,
                SelectedStudentsForAddingGradeId = selectedStudentsForAddingGradeId,
                SubjectStudents = _dbContext.StudentSubjects
                .Where(x => x.ProfessorId == int.Parse(loggedInProfessorId) &&
                x.SubjectId == int.Parse(selectedSubjectId)).Select(x => new StudentSubjectViewModel
                {
                    Name = x.Student.Name,
                    Grade = x.Grade
                }).ToList(),
                StudentsForAddingGrade = _dbContext.StudentSubjects.Include(x => x.Student)
                .Where(x => x.ProfessorId == int.Parse(loggedInProfessorId) &&
                x.SubjectId == int.Parse(selectedSubjectId)).Select(x => x.Student).ToList()
        };

            return View("Views/UserViews/ProfessorView.cshtml", viewModel);
        }

        public IActionResult Index()
        {
            return View();
        }
    }
}
