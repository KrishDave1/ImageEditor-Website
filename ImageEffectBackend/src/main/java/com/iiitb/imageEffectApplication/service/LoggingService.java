package com.iiitb.imageEffectApplication.service;
import com.iiitb.imageEffectApplication.model.LogModel;
//import com.iiitb.imageEffectApplication.repository.LogRepository;
import com.iiitb.imageEffectApplication.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LoggingService {
    @Autowired
    private LogRepository logRepository;
    private List<LogModel> logs = new ArrayList<>();
    public void addLog(String fileName, String effectName, String optionValues) {
        // Get the current date and time
        LocalDateTime currentTime = LocalDateTime.now();

        // Format the current time using a DateTimeFormatter (optional)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        LogModel logModel = new LogModel(formattedTime, fileName, effectName, optionValues);
//        logs.add(logModel);
        logRepository.save(logModel);
        System.out.println("Successfully added a log.");
    }

    public List<LogModel> getAllLogs() {
        return logRepository.findAll();
//       return logs;
    }

    public List<LogModel> getLogsByEffect(String effectName) {
//       List<LogModel> logModels = new ArrayList<LogModel>();
//       for (LogModel logModel : logs) {
//           if (Objects.equals(effectName, logModel.getEffectName())) {
//               logModels.add(logModel);
//           }
//       }
//       return logModels;
         return logRepository.findLogByEffectName(effectName);
    }

    public void clearLogs() {
        logRepository.deleteAll();
//       logs.clear();
    }

    public List<LogModel> getLogsBetweenTimestamps(LocalDateTime startTimestamp, LocalDateTime endTimestamp) {
//        // Filter logs based on the timestamps
//       List<LogModel> filteredLogs = logs.stream()
//               .filter(log -> {
//                   LocalDateTime logDateTime = LocalDateTime.parse(log.getTimestamp(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//                   return (logDateTime.isEqual(startTimestamp) || logDateTime.isAfter(startTimestamp)) &&
//                           (logDateTime.isEqual(endTimestamp) || logDateTime.isBefore(endTimestamp));
//               })
//               .collect(Collectors.toList());
//
//       // Return the filtered logs
//       return filteredLogs;

        // Fetch all logs from the repository
         List<LogModel> allLogs = logRepository.findAll();

         // Filter logs based on the timestamps
         List<LogModel> filteredLogs = allLogs.stream()
                 .filter(log -> {
                     LocalDateTime logDateTime = LocalDateTime.parse(log.getTimestamp(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                     return (logDateTime.isEqual(startTimestamp) || logDateTime.isAfter(startTimestamp)) &&
                             (logDateTime.isEqual(endTimestamp) || logDateTime.isBefore(endTimestamp));
                 })
                 .collect(Collectors.toList());

         // Return the filtered logs
         return filteredLogs;
    }
}
