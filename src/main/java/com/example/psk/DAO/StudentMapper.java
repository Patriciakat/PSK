//package com.example.psk.DAO;
//
//import com.example.psk.entities.Student;
//import org.apache.ibatis.annotations.*;
//import java.util.List;
//
//@Mapper
//public interface StudentMapper {
//    @Select("SELECT * FROM STUDENT")
//    @Results({
//            @Result(property = "id", column = "id"),
//            @Result(property = "name", column = "name"),
//            @Result(property = "studentId", column = "student_id"),
//            @Result(property = "degree", column = "degree_id",
//                    one = @One(select = "com.example.psk.DAO.DegreeMapper.findById")),
//            @Result(property = "exams", column = "id",
//                    many = @Many(select = "com.example.psk.DAO.ExamMapper.findExamsByStudentId"))
//    })
//    List<Student> findAll();
//
//    @Select("SELECT * FROM STUDENT WHERE id = #{id}")
//    @Results({
//            @Result(property = "id", column = "id"),
//            @Result(property = "name", column = "name"),
//            @Result(property = "studentId", column = "student_id"),
//            @Result(property = "degree", column = "degree_id",
//                    one = @One(select = "com.example.psk.DAO.DegreeMapper.findById")),
//            @Result(property = "exams", column = "id",
//                    many = @Many(select = "com.example.psk.DAO.ExamMapper.findExamsByStudentId"))
//    })
//    Student findById(Long id);
//
//    @Select("SELECT * FROM STUDENT WHERE degree_id = #{degreeId}")
//    @Results({
//            @Result(property = "id", column = "id"),
//            @Result(property = "name", column = "name"),
//            @Result(property = "studentId", column = "student_id"),
//            @Result(property = "degree", column = "degree_id",
//                    one = @One(select = "com.example.psk.DAO.DegreeMapper.findById")),
//            @Result(property = "exams", column = "id",
//                    many = @Many(select = "com.example.psk.DAO.ExamMapper.findExamsByStudentId"))
//    })
//    List<Student> findByDegreeId(Long degreeId);
//
//    @Insert("INSERT INTO STUDENT (name, student_id, degree_id) VALUES (#{name}, #{studentId}, #{degree.id})")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    void insert(Student student);
//
//    @Update("UPDATE STUDENT SET name=#{name}, student_id=#{studentId}, degree_id=#{degree.id} WHERE id=#{id}")
//    void update(Student student);
//
//    @Delete("DELETE FROM STUDENT WHERE id=#{id}")
//    void delete(Long id);
//
//    // For many-to-many relationship management
//    @Insert("INSERT INTO STUDENT_EXAM (student_id, exam_id) VALUES (#{studentId}, #{examId})")
//    void addExam(@Param("studentId") Long studentId, @Param("examId") Long examId);
//
//    @Delete("DELETE FROM STUDENT_EXAM WHERE student_id=#{studentId} AND exam_id=#{examId}")
//    void removeExam(@Param("studentId") Long studentId, @Param("examId") Long examId);
//}
//
////This Provides the Same CRUD Operations Using MyBatis.