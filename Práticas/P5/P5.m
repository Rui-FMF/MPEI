%% Clear
close all;
clear all;
clc;

%% Version 1&2

arr_len = round(1000/0.8);      % 0.8 = fator de carga
arr = zeros(1,arr_len);
alpha = ['A':'Z','a':'z'];
pmfPT=pmfLetrasPT({'pg21209.txt','pg26017.txt'},alpha);

for i=1:1000
    j = mod(string2hash(RandKey_v2(pmfPT),'djb2'),arr_len)+1;  %change to RandKey_v1 for version 1 and RandKey_v2(pmfPT) for version 2
    arr(j) = arr(j) + 1;                                        %for version 3 write sdbm instead of djb2
    subplot(1,2,1);
    plot(1:arr_len,arr);
    drawnow;
    subplot(1,2,2);
    histogram(arr);
    drawnow;
end

h = histcounts(arr);
figure();
stem(0:length(h)-1,h/sum(h));

x = zeros(1,length(h)-1);
for k=1:length(h)-1
    x(k) = h(k+1)*(k+1);
end

m = sum(x)/(sum(h)-h(1));
mx2 = sum(x.^2)/(sum(h)-h(1));
var = mx2 - m^2;



