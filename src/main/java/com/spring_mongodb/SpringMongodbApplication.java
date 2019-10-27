package com.spring_mongodb;

import com.spring_mongodb.exception.ItemNotFoundException;
import com.spring_mongodb.model.Task;
import com.spring_mongodb.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringMongodbApplication implements CommandLineRunner {
    private final String MSG_ERROR = "Task name %s not found";
    @Autowired
    private final TaskRepository taskRepository;
    private final Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(SpringMongodbApplication.class, args);
    }

    @Override
    public void run(String... args) {

        int op = 0;
        do {
            System.out.println("Choose options below");
            System.out.println("-----------------------\n");
            System.out.println("1 - Create task");
            System.out.println("2 - List tasks");
            System.out.println("3 - Delete task by name");
            System.out.println("4 - Update task by name");
            System.out.println("0 - Exit");

            op = reader.nextInt();
            this.reader.nextLine();
            switch (op) {
                case 1:
                    create();
                    break;
                case 2:
                    findAll();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    update();
                    break;
            }
        } while (op != 0);
    }

    private void update() {
        System.out.println("Task name:");
        String name = this.reader.nextLine();

        final Task task = this.consultTask(name);

        System.out.println("New name:");
        String newName = this.reader.nextLine();
        task.setName(newName);

        taskRepository.save(task);
        System.out.println("Updated!");
    }

    private void delete() throws ItemNotFoundException {
        System.out.println("Task name for delete:");
        final String name = this.reader.nextLine();
        final Task task = this.consultTask(name);
        taskRepository.delete(task);
        System.out.println("Deleted!");
    }

    private void findAll() {
        List<Task> tasks = taskRepository.findAll();
        for (Task task : tasks) System.out.println(task);
    }

    private void create() throws RuntimeException {
        System.out.println("Task name: ");
        final String name = this.reader.nextLine();
        taskRepository.insert(new Task(name));
    }

    private Task consultTask(String name) {
        Task task = taskRepository.findByName(name);
        if (StringUtils.isEmpty(task))
            throw new ItemNotFoundException(String.format(MSG_ERROR, name));
        return task;
    }
}
