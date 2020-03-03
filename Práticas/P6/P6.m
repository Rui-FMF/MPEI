%% Clear
close all;
clear all;
clc;

%% Ex1

paises={'Alemanha','Espanha','Portugal'};
paises_test={'Portugal','Suiça','Argentina'};

n = 100;
k = 3;

F = init(n);

for i=1:length(paises)
    F = addElement(paises{i},F,k);
end

for i=1:length(paises_test)
    aux = itsElement(paises_test{i},F,k);
    if aux==1
        fprintf('%s is in the set\n',paises_test{i})
    else
        fprintf('%s is not in the set\n',paises_test{i});
    end
end