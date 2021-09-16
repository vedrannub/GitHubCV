using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplicationStudentsMVC.Models;

namespace WebApplicationStudentsMVC.Controllers
{
    public class StudentServiceController : Controller
    {

        private readonly StudentServiceDbContext _dbContext;

        public StudentServiceController(StudentServiceDbContext dbContext)
        {

            _dbContext = dbContext;

        }
        public IActionResult Index()
        {
            return View();
        }

        public IActionResult SelectSubject(string selectedSubjectId, string selectedProfessorId)
        {

            var students = _dbContext.StudentSubjects.Include(x => x.Student)
                .Where(x => x.SubjectId == int.Parse(selectedSubjectId))
                .Select(x => x.Student).ToList();

            var viewModel = new StudentServiceViewModel
            {
                Professors = _dbContext.Professors.ToList(),
                FreeSubjects = _dbContext.Subjects.Where(x => !x.ProfessorId.HasValue).ToList(),
                Subjects = _dbContext.Subjects.ToList(),
                SelectedSubjectStudents = students,
                SelectedSubjectId = selectedSubjectId,
                SelectedProfessorId = selectedProfessorId,
                SelectedProfessorSubjects = _dbContext.Subjects.Where(x => x.ProfessorId.HasValue && !string.IsNullOrEmpty(selectedProfessorId) && x.ProfessorId == int.Parse(selectedProfessorId)).ToList(),
                SelectedSubjectNotAssingedStudents = _dbContext.Students.Include(x => x.StudentSubjects)
                .Where(x => !x.StudentSubjects.Any(y => y.SubjectId == int.Parse(selectedSubjectId))).ToList()
            };

            return View("Views/UserViews/StudentsServiceView.cshtml", viewModel);
        }

        public IActionResult AddStudentToSubject(string selectedProfessorId, string selectedSubjectId, string selectedSubjectStudentId)
        {
            var subject = _dbContext.Subjects.Include(x => x.Professor).FirstOrDefault(x => x.Id == int.Parse(selectedSubjectId));

            var studentSubject = new StudentSubject
            {
                Grade = null,
                ProfessorId = subject.ProfessorId,
                StudentId = int.Parse(selectedSubjectStudentId),
                SubjectId = subject.Id
            };

            _dbContext.Update(studentSubject);
            _dbContext.SaveChanges();

            var viewModel = new StudentServiceViewModel
            {
                Professors = _dbContext.Professors.ToList(),
                FreeSubjects = _dbContext.Subjects.Where(x => !x.ProfessorId.HasValue).ToList(),
                SelectedProfessorId = selectedProfessorId,
                Subjects = _dbContext.Subjects.ToList(),
                SelectedProfessorSubjects = _dbContext.Subjects.Where(x => x.ProfessorId.HasValue && !string.IsNullOrEmpty(selectedProfessorId) && x.ProfessorId == int.Parse(selectedProfessorId)).ToList(),
                SelectedSubjectStudents = _dbContext.StudentSubjects.Where(x => x.SubjectId == subject.Id).Select(x => x.Student).ToList(),
                SelectedSubjectId = selectedSubjectId,
                SelectedSubjectStudentId = selectedSubjectId,
                SelectedSubjectNotAssingedStudents = _dbContext.Students.Include(x => x.StudentSubjects)
                .Where(x => !x.StudentSubjects.Any(y => y.SubjectId == int.Parse(selectedSubjectId))).ToList()
            };

            return View("Views/UserViews/StudentsServiceView.cshtml", viewModel);
        }

        public IActionResult AddSubjectToProfessor(string selectedProfessorId,string selectedProfessorSubjectId)
        {
            var professor = _dbContext.Professors.Include(x => x.Subjects).FirstOrDefault(x => x.Id == int.Parse(selectedProfessorId));
            var subject = _dbContext.Subjects.FirstOrDefault(x => x.Id == int.Parse(selectedProfessorSubjectId));

            professor.Subjects.Add(subject);

            _dbContext.Update(professor);
            _dbContext.SaveChanges();


            var viewModel = new StudentServiceViewModel
            {
                Professors = _dbContext.Professors.ToList(),
                FreeSubjects = _dbContext.Subjects.Where(x => !x.ProfessorId.HasValue).ToList(),
                SelectedProfessorId = selectedProfessorId,
                Subjects = _dbContext.Subjects.ToList(),
                SelectedProfessorSubjects = _dbContext.Subjects.Where(x => x.ProfessorId.HasValue && !string.IsNullOrEmpty(selectedProfessorId) && x.ProfessorId == int.Parse(selectedProfessorId)).ToList()
            };

            return View("Views/UserViews/StudentsServiceView.cshtml", viewModel);
        }

        public IActionResult SelectProfessor(string selectedProfessorId)
        {

            var viewModel = new StudentServiceViewModel
            {
                Professors = _dbContext.Professors.ToList(),
                FreeSubjects = _dbContext.Subjects.Where(x => !x.ProfessorId.HasValue).ToList(),
                SelectedProfessorId = selectedProfessorId,
                Subjects = _dbContext.Subjects.ToList(),
                SelectedProfessorSubjects = _dbContext.Subjects.Where(x => x.ProfessorId.HasValue && !string.IsNullOrEmpty(selectedProfessorId) && x.ProfessorId == int.Parse(selectedProfessorId)).ToList()
            };

            return View("Views/UserViews/StudentsServiceView.cshtml", viewModel);
        }
    }
}
