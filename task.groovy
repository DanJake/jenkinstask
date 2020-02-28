pipeline{
    agent any
    options{
    timestamps()
    }
    parameters {
        string(name: 'list', defaultValue: '', description: 'list')
    }
    stages{
        stage('Maks') {
            steps{
                script{
                    def MyList = params.list.split(" ")
                    if (MyList.size() < 3)
                        error('please print more argument')
                    else{
                        int[] mas = new int[MyList.size()]
                        int[] res = new int[3]
                        boolean flag = false
                        def buf
                        int i = 0
                        MyList.each{
                                try{
                                    it = it.toInteger()
                                } catch(Exception e) {
                                    error('Not a number')
                                }
                                mas[i] = it
                                i++
                        }
                        while (!flag) {
                        flag = true
                            for (i = 0; i < mas.length-1; i++) {
                                if(mas[i] < mas[i+1]) {
                                    flag = false

                                    buf = mas[i]
                                    mas[i] = mas[i+1]
                                    mas[i+1] = buf
                                }
                            }
                        }
                    res[0] = mas[0]
                    int j = 1
                    for (i=1; i < mas.length; i++) {
                        if (mas[i] < mas[i-1]){
                            res[j] = mas[i]
                            j++
                        }
                        if (res[2] == mas[i])
                        break
                    }
                    echo "$res"
                    }
                }
            }
        }
    }
}
