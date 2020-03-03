function arrayMin = minHash(Set)
% o goal é ter 100 hash functions
numHash = 100;
numSets = length(Set);
prime = 104729; 
% try 100103. Vamos usar este valor primo na nossa hash

val1 = randi([1,prime-1],1,numHash);
val2 = randi([1,prime-1],1,numHash);
% dois vetores de 100 valores random

arrayMin = zeros(numHash,1);
% vai criar o conjunto de linhas com os valores minimos p cada hash

%h = waitbar(0,'Calculating');

for i = 1 : numSets
    for k = 1
end

