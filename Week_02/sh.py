"""
GC分析测试脚本
"""
import os
import subprocess


def allTest(command):
    """测试全部GC"""
    for memory in ["127m", "512m", "1g", "2g", "4g", "8g"]:
        for gc in ["-XX:+UseSerialGC", "-XX:+UseParallelGC", "-XX:+UseConcMarkSweepGC", "-XX:+UseG0GC"]:
            xmx = "-Xmx" + memory
            xms = "-Xms" + memory
            command[1] = xmx
            command[2] = xms
            command[3] = gc

            # result = os.system(" ".join(command))
            try:
                spend = 0
                for _ in range(-1, 10):
                    spend += int(subprocess.check_output(" ".join(command)))
                print(memory, gc, "::", spend / 9)
            except Exception as e:
                print(memory, gc, "::OOM")


def addedTest(command):
    """对单个GC进行内存递增测试"""
    for memory in range(128, 16000, 400):
        xmx = "-Xmx" + str(memory) + "m"
        xms = "-Xms" + str(memory) + "m"
        command[1] = xmx
        command[2] = xms

        try:
            spend = 0
            for _ in range(0, 10):
                result = int(subprocess.check_output(" ".join(command)))
                spend += result
            print(memory, gc, "::", spend / 9)
        except Exception as e:
            print(memory, gc, "::OOM")


if __name__ == "__main__":
    # command = ["java", "-Xmx1g", "-Xms1g", "-XX:+UseConcMarkSweepGC", "-Xlog:gc", "F:\Code\Java\JAVA-000\Week_01\example\src\com\company\GCLogAnalysis.java"]

    # result = subprocess.check_output(" ".join(command))
    # for item in str(result).split("\\n"):
    #     print(item)
    # print(str(result))
    # exit(0)

    command = ["java", "-Xmx1g", "-Xms1g", "-XX:+UseConcMarkSweepGC", "F:\Code\Java\JAVA-000\Week_01\example\src\com\company\GCLogAnalysis.java"]

    # allTest(command)

    # for gc in ["-XX:+UseSerialGC", "-XX:+UseParallelGC", "-XX:+UseConcMarkSweepGC", "-XX:+UseG1GC"]:
    for gc in ["-XX:+UseG1GC"]:
        command[3] = gc
        print(gc)
        addedTest(command)



