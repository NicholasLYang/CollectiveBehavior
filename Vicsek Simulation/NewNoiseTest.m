o = zeros(1000);
for x = 1:1000
alpha = 1.6;
gamma = 3;
lambda = 0.1;
randNum = rand();
       if randNum < lambda
           r = 0;
       else
           r = randn();
       end
       o(x + 1) = (1 - alpha) * o(x) + randn() + gamma * r;
end
plot(o);