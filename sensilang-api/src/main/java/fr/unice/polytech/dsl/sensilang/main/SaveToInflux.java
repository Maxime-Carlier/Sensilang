package fr.unice.polytech.dsl.sensilang.main;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SaveToInflux {

    private InfluxDB influxDB;
    private String dbName;
    private String rpName;

    public SaveToInflux() {
        influxDB = InfluxDBFactory.connect("http://localhost:8086");
        this.dbName = "influx";
        influxDB.createDatabase(dbName);
        this.rpName = "aRetentionPolicy";
        influxDB.createRetentionPolicy(rpName, dbName, "30d", "30m", 2, true);
    }

    public void save(List<SensilangPoint> points) {
        BatchPoints batchPoints = BatchPoints
                .database(dbName)
                .tag("async", "true")
                .retentionPolicy(rpName)
                .consistency(InfluxDB.ConsistencyLevel.ALL)
                .build();
        points.forEach(sensilangPoint -> {
            Point point = Point.measurement(sensilangPoint.getSensor())
                    .time(sensilangPoint.getTime(), TimeUnit.MILLISECONDS)
                    .addField("value", sensilangPoint.getValue())
                    .build();
            batchPoints.point(point);
        });
        influxDB.write(batchPoints);
        System.out.println("Saved to InfluxDB");
    }
}
